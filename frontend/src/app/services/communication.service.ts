import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommunicationService {

  private subject = new Subject<any>();

  constructor() { }

  send(object: any) { this.subject.next(object); }

  get(): Observable<any> { return this.subject.asObservable(); }

}
