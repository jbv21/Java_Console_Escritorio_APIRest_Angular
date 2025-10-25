export interface UbigeoModel {
  id?: UbigeoIdModel;
  nombreDepartamento?: string;
  nombreProvincia?: string;
  nombreDistrito?: string;
}

export interface UbigeoIdModel {
    idDepartamento?: string;
    idProvincia?: string;
    idDistrito?: string;
}
