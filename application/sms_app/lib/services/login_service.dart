import 'package:sms_app/dao/phone_list_repository.dart';

class LogInService {

  PhoneListRepository _rep;

  LogInService(this._rep);

  List<num> getPointsPhones() {
    return _rep.getPhoneNumbers();
  }
}
