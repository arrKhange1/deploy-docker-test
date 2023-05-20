import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'docker-deploy-test';

  protected response = '';

  public ngOnInit(): void {
     (async () => {
        const response = await fetch("https://jsonplaceholder.typicode.com/todos/1");
        const strValue = await response.text();
        this.response = strValue;
     }) ()
  }
}
