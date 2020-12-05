import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Person } from 'src/app/models/Person';
import { CommunicationService } from 'src/app/services/communication.service';
import { DatabaseService } from 'src/app/services/database.service';

@Component({
  selector: 'app-person-updater',
  templateUrl: './person-updater.component.html',
  styleUrls: ['./person-updater.component.css']
})
export class PersonUpdaterComponent implements OnInit {


  personForm!: FormGroup;

  person: Person = null;


  constructor(
    private formBuilder: FormBuilder,
    private communication: CommunicationService,
    private database: DatabaseService,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {


    this.communication.get().subscribe(object => {

      if (object.OPERATION === 'PERSON UPDATER' && object.DATA) {

        this.configPersonForm();
        this.setPerson(object.DATA);

      }

    });

  }

  setPerson(person: Person) { this.person = person; }

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

  async onUpdatePerson() {

    this.validatePersonForm();

    if (this.personForm.valid) {

      this.person.dni = this.person.dni.toString();
      this.person.age = this.person.age.toString();
      this.person.birthdate = this.person.birthdate.toString();

      const person: Person = await this.database.updatePerson(this.person);

      if (person) {

        this.snackBar.open(`Los datos de ${person.fullname} han sido actualizados en la base de datos`, 'OK', { duration: 5000 });

        this.communication.send('UPDATE PERSON');

        this.person = null;

      } else {

        this.snackBar.open('Algo anda mal', null, { duration: 5000 });

      }

    }

  }

}
