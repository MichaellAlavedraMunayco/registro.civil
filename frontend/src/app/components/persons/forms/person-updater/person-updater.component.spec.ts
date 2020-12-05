import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonUpdaterComponent } from './person-updater.component';

describe('PersonUpdaterComponent', () => {
  let component: PersonUpdaterComponent;
  let fixture: ComponentFixture<PersonUpdaterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PersonUpdaterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonUpdaterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
