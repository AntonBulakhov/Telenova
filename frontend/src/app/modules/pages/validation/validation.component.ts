import {Component, OnInit} from '@angular/core';
import {ValidationErrorModel} from "../../../dto/validationerror.model";
import {HistoryDtoModel} from "../../../dto/historyDto.model";

@Component({
  selector: 'app-validation',
  templateUrl: './validation.component.html',
  styleUrls: ['./validation.component.css']
})
export class ValidationComponent implements OnInit {

  public errorRecords = new Array<ValidationErrorModel>();

  constructor() { }

  ngOnInit() {
    this.errorRecords.push(new ValidationErrorModel("user", "price", "0,50", "0,53"));
  }

}
