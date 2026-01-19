import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-bad-code',
  templateUrl: './bad-code.component.html',
  styleUrls: ['./bad-code.component.scss']
})
export class BadCodeComponent implements OnInit {

  // Violation 1: Using 'var' instead of 'let' or 'const'
  var title = 'Test Component'; 

  constructor() { }

  ngOnInit(): void {
    this.processData();
  }

  processData() {
    const data = { id: 1, name: 'Angular' };

    // Violation 2: Leftover console.log
    console.log('Fetching data...', data); 

    if (data.id === 1) {
      // Violation 3: Hardcoded debugger breakpoint
      debugger; 
      
      this.updateUI();
    }
  }

  updateUI() {
    // Violation 4: Using native alert instead of a UI service
    alert('Data processed!'); 
  }
}

// Violation 5: Focused test suite (often accidental)
/* fdescribe('BadCodeComponent', () => {
  it('should create', () => {
    // ...
  });
});
*/