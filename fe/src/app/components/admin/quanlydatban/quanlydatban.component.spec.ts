import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuanlydatbanComponent } from './quanlydatban.component';

describe('QuanlydatbanComponent', () => {
  let component: QuanlydatbanComponent;
  let fixture: ComponentFixture<QuanlydatbanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QuanlydatbanComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QuanlydatbanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
