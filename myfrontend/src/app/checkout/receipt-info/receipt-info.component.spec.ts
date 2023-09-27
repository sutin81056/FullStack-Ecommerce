import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReceiptInfoComponent } from './receipt-info.component';

describe('ReceiptInfoComponent', () => {
  let component: ReceiptInfoComponent;
  let fixture: ComponentFixture<ReceiptInfoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ReceiptInfoComponent]
    });
    fixture = TestBed.createComponent(ReceiptInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
