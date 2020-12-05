import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Person } from 'src/app/models/Person';
import { CommunicationService } from 'src/app/services/communication.service';
import { DatabaseService } from 'src/app/services/database.service';

@Component({
  selector: 'app-person-creator',
  templateUrl: './person-creator.component.html',
  styleUrls: ['./person-creator.component.css']
})
export class PersonCreatorComponent implements OnInit {


  personForm!: FormGroup;

  person: Person = new Person();


  constructor(
    private formBuilder: FormBuilder,
    private communication: CommunicationService,
    private database: DatabaseService,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {

    this.person.gender = 'Masculino';

    this.configPersonForm();

  }

  configPersonForm(): void {

    this.personForm = this.formBuilder.group({

      dni: new FormControl('', [Validators.required, Validators.pattern(new RegExp('[0-9]{8}'))]),
      fullname: new FormControl('', [Validators.required, Validators.maxLength(255)]),
      email: new FormControl('', [Validators.required, Validators.email]),
      birthdate: new FormControl('', [Validators.required]),
      age: new FormControl('', [Validators.required]),
      gender: new FormControl('', [Validators.required])

    });

  }

  validatePersonForm(): void {

    for (const [key, control] of Object.entries(this.personForm.controls)) {
      control.markAsDirty();
      control.updateValueAndValidity();
    }

  }

  resetPersonForm(): void {

    this.personForm.reset();

    Object.keys(this.personForm.controls).forEach(key => {
      this.personForm.controls[key].setErrors(null);
    });

  }

  getDNIErrorMessage() {

    if (this.personForm.get('dni').hasError('required')) {
      return 'Ingrese su DNI';
    }

    if (!this.personForm.get('dni').hasError('pattern')) {
      return 'DNI incorrecto';
    }

  }

  getEmailErrorMessage() {

    if (this.personForm.get('email').hasError('required')) {
      return 'Ingrese su email';
    }

    if (this.personForm.get('email').hasError('email')) {
      return 'Email incorrecto';
    }

  }

  async onSavePerson() {

    this.validatePersonForm();

    if (this.personForm.valid) {

      this.person.dni = this.person.dni.toString();
      this.person.age = this.person.age.toString();
      this.person.birthdate = this.person.birthdate.toString();

      const person: Person = await this.database.addPerson(this.person);

      if (person) {

        this.person = new Person();

        this.resetPersonForm();

        this.snackBar.open(`${person.fullname} ha sido agregado/a a la base de datos`, 'OK', { duration: 5000 });

        this.communication.send('ADD PERSON');

      } else {

        this.snackBar.open('Algo anda mal', null, { duration: 5000 });

      }


    }

  }

}
