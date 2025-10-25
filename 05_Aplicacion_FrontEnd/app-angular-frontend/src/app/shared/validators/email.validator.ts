import { AbstractControl, ValidatorFn, ValidationErrors } from '@angular/forms';

export function emailValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {

    // Correos invalidos (gmail, outlook, yahoo)
    // Correos validos (gmail, outlook, yahoo)

    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    const isValid = emailRegex.test(control.value);
    return isValid ? null : { 'customEmail': { value: control.value } };
  };
}
