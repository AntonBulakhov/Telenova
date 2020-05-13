export class ValidationErrorModel {
  user: string;
  field: string;
  wrongValue: string;
  trueValue: string;

  constructor(user: string, field: string, wrongValue: string, trueValue: string) {
    this.user = user;
    this.field = field;
    this.wrongValue = wrongValue;
    this.trueValue = trueValue;
  }
}
