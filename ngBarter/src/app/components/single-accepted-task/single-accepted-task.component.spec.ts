import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleAcceptedTaskComponent } from './single-accepted-task.component';

describe('SingleAcceptedTaskComponent', () => {
  let component: SingleAcceptedTaskComponent;
  let fixture: ComponentFixture<SingleAcceptedTaskComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SingleAcceptedTaskComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SingleAcceptedTaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
