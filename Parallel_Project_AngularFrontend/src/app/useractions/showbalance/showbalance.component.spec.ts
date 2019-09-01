import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowbalanceComponent } from './showbalance.component';

describe('ShowbalanceComponent', () => {
  let component: ShowbalanceComponent;
  let fixture: ComponentFixture<ShowbalanceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowbalanceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowbalanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
