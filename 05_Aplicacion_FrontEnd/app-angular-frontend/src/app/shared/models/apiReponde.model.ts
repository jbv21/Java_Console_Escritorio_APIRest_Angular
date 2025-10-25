
export interface ApiResponseModel<T> {
    estado: boolean;
    titulo: string;
    mensaje: string;
    resultado: T;
}
