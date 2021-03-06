package jp.co.freemind.calico.jackson.ser;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import jp.co.freemind.calico.jackson.JsonObject;

/**
 * Created by kakusuke on 15/08/07.
 */
public class JsonObjectSerializer extends JsonSerializer<JsonObject<?>> {
  @Override
  public void serialize(JsonObject<?> value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
    gen.getCodec().writeValue(gen, value.get());
  }
}
