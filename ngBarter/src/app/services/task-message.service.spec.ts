import { TestBed } from '@angular/core/testing';

import { TaskMessageService } from './task-message.service';

describe('TaskMessageService', () => {
  let service: TaskMessageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TaskMessageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
