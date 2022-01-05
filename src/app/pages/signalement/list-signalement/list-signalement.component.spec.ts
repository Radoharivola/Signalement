import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListSignalementComponent } from './list-signalement.component';

describe('ListSignalementComponent', () => {
  let component: ListSignalementComponent;
  let fixture: ComponentFixture<ListSignalementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListSignalementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListSignalementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
