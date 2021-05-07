import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RenewPermisComponent } from './renew-permis.component';

describe('RenewPermisComponent', () => {
  let component: RenewPermisComponent;
  let fixture: ComponentFixture<RenewPermisComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RenewPermisComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RenewPermisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
