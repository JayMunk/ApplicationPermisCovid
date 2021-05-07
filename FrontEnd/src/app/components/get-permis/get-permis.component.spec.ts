import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetPermisComponent } from './get-permis.component';

describe('GetPermisComponent', () => {
  let component: GetPermisComponent;
  let fixture: ComponentFixture<GetPermisComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetPermisComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GetPermisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
