import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Person } from 'src/app/models/Person';
import { CommunicationService } from 'src/app/services/communication.service';
import { DatabaseService } from 'src/app/services/database.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { ContactsComponent } from 'src/app/components/contacts/contacts.component';


@Component({
  selector: 'app-person-table',
  templateUrl: './person-table.component.html',
  styleUrls: ['./person-table.component.css']
})
export class PersonTableComponent implements AfterViewInit, OnInit {


  personList: Person[] = [];
  columns: string[] = ['DNI', 'Nombres', 'Email', 'Fecha de Nacimiento', 'Edad', 'Género', 'Opciones'];
  dataSource = new MatTableDataSource<Person>(this.personList);

  loading = false;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private snackBar: MatSnackBar,
    private communication: CommunicationService,
    private database: DatabaseService,
    private dialog: MatDialog
  ) {

  }

  ngAfterViewInit() {

    this.dataSource.paginator = this.paginator;

  }

  ngOnInit(): void {

    this.setPersonList();

    this.communication.get().subscribe(async object => {

      if (
        object as string === 'ADD PERSON' ||
        object as string === 'UPDATE PERSON'
      ) {

        await this.setPersonList();

      }

    });

  }

  async setPersonList() {

    this.loading = true;

    this.personList = await this.database.getPersonList();

    this.dataSource = new MatTableDataSource<Person>(this.personList);

    this.dataSource.paginator = this.paginator;

    this.loading = false;

  }

  onUpdatePerson(person: Person) {

    this.communication.send({ OPERATION: 'PERSON UPDATER', DATA: person });

  }

  async onDeletePerson(personId: string) {

    await this.database.deletePerson(personId);

    this.snackBar.open(`Se ha eliminado de la base de datos`, 'OK', { duration: 5000 });

    await this.setPersonList();

  }

  onViewContactList(personId: string) {

    const dialogRef = this.dialog.open(ContactsComponent, { data: { personId: personId } });

    dialogRef.afterClosed().subscribe(result => { });

  }

}
