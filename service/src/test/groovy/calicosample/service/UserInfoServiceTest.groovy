package calicosample.service

import helper.ServiceTest
import jp.co.freemind.calico.guice.InjectUtil
import spock.lang.Shared
import spock.lang.Specification
/**
 * Created by tasuku on 15/04/09.
 */
@ServiceTest
class UserInfoServiceTest extends Specification {

  @Shared
  def UserInfoService service = InjectUtil.getInstance(UserInfoService)

  def "getRecord"() {
//    setup:
//    println DataFactory.create("user", [id: 666])
//    println DataFactory.create("admin", [id: 777])
//
//    when:
//    def record = service.getRecord(new UserInfoIdForm(id: 666))
//    then:
//    assert record.id == 666
//
//    when:
//    service.getRecord(new UserInfoIdForm(id: 888))
//    then:
//    thrown(NoResultException)
  }
}
