import {Component, Input, OnInit} from '@angular/core';
import {OfferDTOModel} from "../../../dto/offerdto.model";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
  @Input() mobileTariff: OfferDTOModel;

  constructor() { }

  ngOnInit() {
  }

}
