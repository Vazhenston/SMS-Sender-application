import 'package:sms_app/dao/phone_list_repository.dart';

class PhoneListRepositoryMock implements PhoneListRepository{
  @override
  List<num> getPhoneNumbers() {
    return [1, 2, 3, 4, 5, 6]; //Here are pick-up points phone numbers. Light authorisation
  }
}