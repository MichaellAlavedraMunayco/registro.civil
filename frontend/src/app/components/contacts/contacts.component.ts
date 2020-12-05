import { Component, OnInit } from '@angular/core';
import { CommunicationService } from 'src/app/services/communication.service';

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent implements OnInit {


  formIndex = 0;


  constructor(private communication: CommunicationService) { }

  ngOnInit(): void {

    this.communication.get().subscribe(object => {

      if (object.OPERATION === 'CONTACT UPDATER') {

        this.formIndex = 1;

      }

    });

  }

}
