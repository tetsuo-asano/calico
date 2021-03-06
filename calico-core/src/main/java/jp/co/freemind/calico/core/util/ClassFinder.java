package jp.co.freemind.calico.core.util;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public final class ClassFinder {
  private ClassFinder() {

  }

  public static List<Class<?>> findClasses(String... packageNames) {
    List<Class<?>> classes = new ArrayList<Class<?>>();
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    try {
      for (String packageName : packageNames) {
        String dirName = packageName.replace('.', '/');
        Enumeration<URL> list = classLoader.getResources(dirName);
        for (URL resource : Collections.list(list)) {
          String protocol = resource.getProtocol();
          if ("file".equals(protocol)) {
            File file = Paths.get(resource.toURI()).toFile();
            classes.addAll(findClasses(packageName, file));
          } else if ("jar".equals(protocol)) {
            JarURLConnection jarUrlConnection = (JarURLConnection) resource.openConnection();
            classes.addAll(findClassesInJar(packageName, jarUrlConnection.getJarFile()));
          }
        }
      }
    } catch (IOException | ClassNotFoundException | URISyntaxException e) {
      e.printStackTrace();
    }

    return classes;
  }

  private static List<Class<?>> findClasses(String packageName, File directory) throws ClassNotFoundException {
    List<Class<?>> classes = new ArrayList<>();
    File[] listFiles = directory.listFiles();
    if (listFiles == null) {
      throw new IllegalStateException("invalid directory " + directory);
    }
    for (File child : listFiles) {
      if (child.isDirectory()) {
        classes.addAll(findClasses(packageName + "." + child.getName(), child));
      } else if (child.isFile()) {
        String fileName = child.getName();
        if (isClass(fileName)) {
          String className = packageName + "." + fileName.substring(0, fileName.length() - ".class".length());
          Class<?> clazz = Class.forName(className);
          if (!fileName.endsWith("Test.class")) {
            classes.add(clazz);
          }
        }
      }
    }
    return classes;
  }

  private static List<Class<?>> findClassesInJar(String packageName, JarFile jarFile) throws IOException, ClassNotFoundException {
    List<Class<?>> classes = new ArrayList<Class<?>>();
    String dirName = packageName.replace('.', '/');
    Enumeration<JarEntry> entries = jarFile.entries();
    for (JarEntry entry : Collections.list(entries)) {
      String entryName = entry.getName();
      if (isClass(entryName) && entryName.startsWith(dirName)) {
        String fileName = entryName.replaceFirst(dirName + "/", "");
        classes.add(loadClass(fileName, packageName));
      }
    }
    jarFile.close();
    return classes;
  }

  private static boolean isClass(String entryName) {
    return entryName.endsWith(".class") && !entryName.contains("$");
  }

  private static Class<?> loadClass(String fileName, String packageName) throws ClassNotFoundException {
    String className = packageName + "." + fileName.substring(0, fileName.length() - ".class".length()).replace('/', '.');
    return Class.forName(className);
  }
}
