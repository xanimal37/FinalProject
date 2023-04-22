import { TestBed } from '@angular/core/testing';

import { AcceptedTaskService } from './accepted-task.service';

describe('AcceptedTaskService', () => {
  let service: AcceptedTaskService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AcceptedTaskService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
