import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
// Environment
import { environment } from 'src/environments/environment';
import { Person } from '../models/Person';
import { Contact } from '../models/Contact';

@Injectable({
  providedIn: 'root'
})
export class DatabaseService {


  host: string = environment.endpoint;


  constructor(private http: HttpClient) { }

  // * Person Request Functions

  addPerson = (person: Person): Promise<Person> => this.http.post<Person>(this.host.concat('person/add'), person).toPromise();

  getPersonList = (): Promise<Person[]> => this.http.get<Person[]>(this.host.concat('person/get/all')).toPromise();

  updatePerson = (person: Person): Promise<Person> => this.http.put<Person>(this.host.concat('person/update'), person).toPromise();

  deletePerson = (personId: string) => this.http.delete(this.host.concat('person/delete/', personId)).toPromise();

  // * Contact Request Functions

  addContact = (contact: Contact): Promise<Contact> => this.http.post<Contact>(this.host.concat('contact/add'), contact).toPromise();

  getContactListByPerson = (personId: string): Promise<Contact[]> =>
    this.http.get<Contact[]>(this.host.concat('contact/get/all/by/person/', personId)).toPromise()

  updateContact = (contact: Contact): Promise<Contact> => this.http.put<Contact>(this.host.concat('contact/update'), contact).toPromise();

  deleteContact = (contactId: string) => this.http.delete(this.host.concat('contact/delete/', contactId)).toPromise();

}
