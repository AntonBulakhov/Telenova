export class HistoryDtoModel {
  offer: string;
  payment: string;
  date: string;

  constructor(offer: string, payment: string, date: string) {
    this.offer = offer;
    this.payment = payment;
    this.date = date;
  }
}
