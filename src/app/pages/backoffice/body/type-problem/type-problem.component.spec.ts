import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TypeProblemComponent } from './type-problem.component';

describe('TypeProblemComponent', () => {
  let component: TypeProblemComponent;
  let fixture: ComponentFixture<TypeProblemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TypeProblemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TypeProblemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
