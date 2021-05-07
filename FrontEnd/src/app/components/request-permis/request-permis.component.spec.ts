import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestPermisComponent } from './request-permis.component';

describe('RequestPermisComponent', () => {
  let component: RequestPermisComponent;
  let fixture: ComponentFixture<RequestPermisComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RequestPermisComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RequestPermisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
