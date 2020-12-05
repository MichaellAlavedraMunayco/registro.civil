import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Contact } from 'src/app/models/Contact';
import { CommunicationService } from 'src/app/services/communication.service';
import { DatabaseService } from 'src/app/services/database.service';

@Component({
  selector: 'app-contact-updater',
  templateUrl: './contact-updater.component.html',
  styleUrls: ['./contact-updater.component.css']
})
export class ContactUpdaterComponent implements OnInit {


  contactForm!: FormGroup;

  localData: any = null;
  personId: string = null;
  contact: Contact = null;


  constructor(
    private formBuilder: FormBuilder,
    private communication: CommunicationService,
    private database: DatabaseService,
    private snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) private data: any
  ) {

    this.localData = { ...data };

  }

  ngOnInit(): void {

    this.personId = this.localData.personId;
    this.configContactForm();

    this.communication.get().subscribe(object => {

      if (object.OPERATION === 'CONTACT UPDATER' && object.DATA) {

        this.setContact(object.DATA);

      }

    });

  }

  setContact(contact: Contact) { this.contact = contact; }

  configContactForm(): void {

    this.contactForm = this.formBuilder.group({

      address: new FormControl('', [Validators.required]),
      homephone: new FormControl('', [Validators.required, Validators.maxLength(10)]),
      phone: new FormControl('', [Validators.required, Validators.maxLength(10)])

    });

  }

  validateContactForm(): void {

    for (const [key, control] of Object.entries(this.contactForm.controls)) {
      control.markAsDirty();
      control.updateValueAndValidity();
    }

  }

  async onUpdateContact() {

    this.validateContactForm();

    if (this.contactForm.valid) {

      this.contact.personId = this.personId;

      const contact: Contact = await this.database.updateContact(this.contact);

      if (contact) {

        this.snackBar.open(`Se actualizaron los  datos de contacto`, 'OK', { duration: 5000 });

        this.communication.send('UPDATE CONTACT');

        this.contact = null;

      } else {

        this.snackBar.open('Algo anda mal', null, { duration: 5000 });

      }


    }

  }

}
