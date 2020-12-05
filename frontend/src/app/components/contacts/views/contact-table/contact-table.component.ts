import { AfterViewInit, Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { CommunicationService } from 'src/app/services/communication.service';
import { DatabaseService } from 'src/app/services/database.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Contact } from 'src/app/models/Contact';

@Component({
  selector: 'app-contact-table',
  templateUrl: './contact-table.component.html',
  styleUrls: ['./contact-table.component.css']
})
export class ContactTableComponent implements AfterViewInit, OnInit {


  localData: any = null;
  personId: string = null;
  contactList: Contact[] = [];
  columns: string[] = ['Domicilio', 'Teléfono Fijo', 'Teléfono Móvil', 'Opciones'];
  dataSource = new MatTableDataSource<Contact>(this.contactList);

  loading = false;

  @ViewChild(MatPaginator) paginator: MatPaginator;


  constructor(
    private snackBar: MatSnackBar,
    private communication: CommunicationService,
    private database: DatabaseService,
    @Inject(MAT_DIALOG_DATA) private data: any
  ) {

    this.localData = { ...data };

  }

  ngAfterViewInit() {

    this.dataSource.paginator = this.paginator;

  }

  async ngOnInit() {

    this.personId = this.localData.personId;

    await this.setContactListByPerson(this.personId);

    this.communication.get().subscribe(async object => {

      if (
        object as string === 'ADD CONTACT' ||
        object as string === 'UPDATE CONTACT'
      ) {

        await this.setContactListByPerson(this.personId);

      }

    });

  }

  async setContactListByPerson(personId: string) {

    if (personId) {

      this.loading = true;

      this.contactList = await this.database.getContactListByPerson(personId);

      this.dataSource = new MatTableDataSource<Contact>(this.contactList);

      this.dataSource.paginator = this.paginator;

      this.loading = false;

    }

  }

  onUpdateContact(contact: Contact) {

    this.communication.send({ OPERATION: 'CONTACT UPDATER', DATA: contact });

  }

  async onDeleteContact(contactId: string) {

    await this.database.deleteContact(contactId);

    this.snackBar.open(`Se ha eliminado de la base de datos`, 'OK', { duration: 5000 });

    await this.setContactListByPerson(this.personId);

  }


}
