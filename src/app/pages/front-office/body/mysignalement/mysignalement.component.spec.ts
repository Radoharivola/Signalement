import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MysignalementComponent } from './mysignalement.component';

describe('MysignalementComponent', () => {
  let component: MysignalementComponent;
  let fixture: ComponentFixture<MysignalementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MysignalementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MysignalementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
