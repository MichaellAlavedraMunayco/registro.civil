import { Component, OnInit } from '@angular/core';
import { CommunicationService } from 'src/app/services/communication.service';

@Component({
  selector: 'app-persons',
  templateUrl: './persons.component.html',
  styleUrls: ['./persons.component.css']
})
export class PersonsComponent implements OnInit {


  formIndex = 0;


  constructor(private communication: CommunicationService) { }

  ngOnInit(): void {

    this.communication.get().subscribe(object => {

      if (object.OPERATION === 'PERSON UPDATER') {

        this.formIndex = 1;

      }

    });

  }

}
