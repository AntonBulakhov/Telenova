import {Component, OnInit} from '@angular/core';
import {OfferingModel} from "../../../models/offering.model";
import {OfferingTypeModel} from "../../../models/offeringtype.model";
import {ValueMeasureModel} from "../../../models/valuemeasure.model";
import {OfferingService} from "../../../services/offering.service";
import {GroupedOfferingsModel} from "../../../dto/groupedofferings.model";
import {SpecificationModel} from "../../../models/specification.model";
import {SpecificationService} from "../../../services/specification.service";

@Component({
  selector: 'app-offeringsconfiguration',
  templateUrl: './offeringsconfiguration.component.html',
  styleUrls: ['./offeringsconfiguration.component.css']
})
export class OfferingsconfigurationComponent implements OnInit {

  public newOffering: OfferingModel = new OfferingModel();
  private groupedOfferings: GroupedOfferingsModel;
  private offeringTypes: OfferingTypeModel[];
  private valueMeasurements: ValueMeasureModel[];
  private specifications: SpecificationModel[];

  constructor(private offeringService: OfferingService,
              private specificationService: SpecificationService) {
  }

  ngOnInit() {
    this.offeringService.getGroupedOfferingsByType().subscribe(value => {
      this.groupedOfferings = value;
    });
    this.offeringService.getAllOfferingTypes().subscribe(value => {
      this.offeringTypes = value as OfferingTypeModel[];
    });
    this.offeringService.getAllValueMeasures().subscribe(value => {
      this.valueMeasurements = value as ValueMeasureModel[];
    });
    this.specificationService.getAllSpecifications().subscribe(value => {
      this.specifications = value as SpecificationModel[];
    });
  }

  createOffering(specId: string, typeId: string, measureId: string) {
    this.newOffering.specification = this.getSpecification(specId);
    this.newOffering.offeringType = this.getOfferingType(typeId);
    this.newOffering.valueMeasure = this.getValueMeasure(measureId);
    this.offeringService.createOffering(this.newOffering).subscribe(value => {
      this.ngOnInit();
    });
  }

  public getSpecification(id: string): SpecificationModel {
    return this.specifications.find(obj => obj.id == id);
  }

  public getOfferingType(id: string): OfferingTypeModel {
    return this.offeringTypes.find(obj => obj.id == id);
  }

  public getValueMeasure(id: string): ValueMeasureModel {
    return this.valueMeasurements.find(obj => obj.id == id);
  }

  deleteOffering(id: string): void {
    this.offeringService.deleteOffering(id).subscribe(value => {
      this.ngOnInit();
    });
  }
}
