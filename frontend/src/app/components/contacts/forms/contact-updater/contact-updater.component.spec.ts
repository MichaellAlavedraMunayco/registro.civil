import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactUpdaterComponent } from './contact-updater.component';

describe('ContactUpdaterComponent', () => {
  let component: ContactUpdaterComponent;
  let fixture: ComponentFixture<ContactUpdaterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContactUpdaterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContactUpdaterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
