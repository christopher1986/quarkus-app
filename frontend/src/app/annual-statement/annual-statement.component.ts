import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { range } from '../util/array.utils';

@Component({
  selector: 'app-annual-statement',
  templateUrl: './annual-statement.component.html',
  styleUrls: ['./annual-statement.component.scss']
})
export class AnnualStatementComponent implements OnInit {

  form!: FormGroup;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      year: ['']
    });
  }

  submit(): void {
    console.log('works');
  }

  get years(): number[] {
    const arraySize: number = 10;
    const yearOfToday: number = new Date().getFullYear();
    return range(arraySize).reverse().map(number => yearOfToday - number);
  }

}
