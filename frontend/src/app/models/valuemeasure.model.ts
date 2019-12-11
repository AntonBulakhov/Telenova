export class ValueMeasureModel {
  id:string;
  measurement: string;

  static cloneBase(model: ValueMeasureModel):ValueMeasureModel {
    const cloneModel: ValueMeasureModel = new ValueMeasureModel();
    cloneModel.id = model.id;
    cloneModel.measurement = model.measurement;
    return cloneModel;
  }
}
