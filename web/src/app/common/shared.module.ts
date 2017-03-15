import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { CalicoModule } from 'calico';

import { AlertModule, ModalModule, DatepickerModule, PopoverModule, TimepickerModule } from 'ng2-bootstrap';

import {
  GrowlModule,
} from 'primeng/primeng';

import { LayoutModule} from './layout/layout.module';

@NgModule({
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    CalicoModule,
    LayoutModule,
    /* PrimeNG START */
    GrowlModule,
    /* PrimeNG END */
    ModalModule,
    PopoverModule,
    AlertModule,
    DatepickerModule,
    TimepickerModule,
  ],
})
export class SharedModule { }
