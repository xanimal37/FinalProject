import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewTaskMessageComponent } from './new-task-message.component';

describe('NewTaskMessageComponent', () => {
  let component: NewTaskMessageComponent;
  let fixture: ComponentFixture<NewTaskMessageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewTaskMessageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewTaskMessageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
