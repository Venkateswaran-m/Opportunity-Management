import { TestBed } from '@angular/core/testing';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { NotificationService } from './notification.service';

describe('NotificationService', () => {
  let service: NotificationService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [   MatSnackBarModule ],
      providers:[NotificationService]
    });
    service=TestBed.get(NotificationService);
    service = TestBed.inject(NotificationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should check if "success" has been called',()=>{
    spyOn(service,'success');
    let res = service.success("success");
    expect(service.success).toHaveBeenCalled();
  });
  it('should check if "warn" has been called',()=>{
    spyOn(service,'warn');
    let res = service.warn("warn");
    expect(service.warn).toHaveBeenCalled();
    
  });
});
