import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AcceptedTaskComponent } from './accepted-task.component';

describe('AcceptedTaskComponent', () => {
  let component: AcceptedTaskComponent;
  let fixture: ComponentFixture<AcceptedTaskComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AcceptedTaskComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AcceptedTaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
