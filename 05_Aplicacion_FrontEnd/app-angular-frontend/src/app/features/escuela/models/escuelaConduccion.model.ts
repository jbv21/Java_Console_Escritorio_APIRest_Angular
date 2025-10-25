import { UbigeoModel } from "../../ubicacion/models/ubigeo.model";

export interface EscuelaConduccionModel {
  id?: number;
  ruc: string;
  nombre: string;
  direccion: string;
  estado: string;
  telefono: string;
  correo: string;
  ubigeo: UbigeoModel;
}
