import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskdashComponent } from './taskdash.component';

describe('TaskdashComponent', () => {
  let component: TaskdashComponent;
  let fixture: ComponentFixture<TaskdashComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TaskdashComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TaskdashComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
