import { Component, OnInit } from '@angular/core';
import {HistoryDtoModel} from "../../../dto/historyDto.model";

@Component({
  selector: 'app-paymenthistory',
  templateUrl: './paymenthistory.component.html',
  styleUrls: ['./paymenthistory.component.css']
})
export class PaymenthistoryComponent implements OnInit {

  public historyDtos = new Array<HistoryDtoModel>();

  constructor() { }

  ngOnInit() {
    this.historyDtos.push(new HistoryDtoModel("Интернет \"Домашний-стандарт\"", "0,53 р.", "4-19-2020"));
    this.historyDtos.push(new HistoryDtoModel("Мобильный \"Стандатр\"", "0,33 р.", "4-19-2020"));
    this.historyDtos.push(new HistoryDtoModel("Интернет \"Домашний-стандарт\"", "0,53 р.", "4-18-2020"));
    this.historyDtos.push(new HistoryDtoModel("Мобильный \"Стандатр\"", "0,33 р.", "4-18-2020"));
    this.historyDtos.push(new HistoryDtoModel("Интернет \"Домашний-стандарт\"", "0,53 р.", "4-17-2020"));
    this.historyDtos.push(new HistoryDtoModel("Мобильный \"Стандатр\"", "0,33 р.", "4-17-2020"));
    this.historyDtos.push(new HistoryDtoModel("Мобильный \"Стандатр\"", "0,33 р.", "4-16-2020"));
    this.historyDtos.push(new HistoryDtoModel("Мобильный \"Стандатр\"", "0,33 р.", "4-15-2020"));
    this.historyDtos.push(new HistoryDtoModel("Мобильный \"Стандатр\"", "0,33 р.", "4-14-2020"));
    this.historyDtos.push(new HistoryDtoModel("Мобильный \"Стандатр\"", "0,33 р.", "4-13-2020"));
  }

}
