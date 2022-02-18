import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SignalementTermineComponent } from './signalement-termine.component';

describe('SignalementTermineComponent', () => {
  let component: SignalementTermineComponent;
  let fixture: ComponentFixture<SignalementTermineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SignalementTermineComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SignalementTermineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
