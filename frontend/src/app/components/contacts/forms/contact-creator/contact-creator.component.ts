import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Contact } from 'src/app/models/Contact';
import { CommunicationService } from 'src/app/services/communication.service';
import { DatabaseService } from 'src/app/services/database.service';

@Component({
  selector: 'app-contact-creator',
  templateUrl: './contact-creator.component.html',
  styleUrls: ['./contact-creator.component.css']
})
export class ContactCreatorComponent implements OnInit {


  contactForm!: FormGroup;

  localData: any = null;
  personId: string = null;
  contact: Contact = new Contact();


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

  }

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

  async onSaveContact() {

    this.validateContactForm();

    if (this.contactForm.valid) {

      this.contact.personId = this.personId;

      const contact: Contact = await this.database.addContact(this.contact);

      if (contact) {

        this.contact = new Contact();

        this.contactForm.reset();

        this.snackBar.open(`Se guardó en la base de datos`, 'OK', { duration: 5000 });

        this.communication.send('ADD CONTACT');

      } else {

        this.snackBar.open('Algo anda mal', null, { duration: 5000 });

      }


    }

  }

}
