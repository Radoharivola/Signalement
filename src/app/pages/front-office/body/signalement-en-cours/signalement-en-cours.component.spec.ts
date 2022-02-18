import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SignalementEnCoursComponent } from './signalement-en-cours.component';

describe('SignalementEnCoursComponent', () => {
  let component: SignalementEnCoursComponent;
  let fixture: ComponentFixture<SignalementEnCoursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SignalementEnCoursComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SignalementEnCoursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
