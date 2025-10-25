import { AbstractControl, ValidatorFn, ValidationErrors } from '@angular/forms';

export function phoneValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const value: string=control.value
    //console.log(value)
    const prefix=value.substring(0,1)
    //console.log(prefix);
    const phone=value.substring(1)
    //console.log(phone);
    if(prefix!='+'){
      //console.log('invalid prefix');
       return { 'phone': { value: control.value } } ;
    }
    const phoneRegex = /^(\+\d{1,2}\s)?\(?\d{3}\)?[\s.-]\d{3}[\s.-]\d{4}$/;
    const isValid = phoneRegex.test(phone);
    return isValid ? null : { 'phone': { value: control.value } };
  };
}

/*
123-456-7890
(123) 456-7890
123 456 7890
123.456.7890
+91 (123) 456-7890
*/

