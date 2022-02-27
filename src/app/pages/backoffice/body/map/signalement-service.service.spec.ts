import { TestBed } from '@angular/core/testing';

import { SignalementServiceService } from './signalement-service.service';

describe('SignalementServiceService', () => {
  let service: SignalementServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SignalementServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
