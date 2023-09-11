import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  baseUrl = 'http://localhost:8080';
  todolist: any[] = [];
  priorities = ['HIGH', 'MEDIUM', 'LOW'];
  texto: String = '';
  prio: String = 'ALL';

  constructor(private http: HttpClient) {}

  convert(unixTimestamp: number) {
    const date = new Date(unixTimestamp);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    const formattedDate = `${year}-${month}-${day}`;

    return formattedDate;
  }

  filtradora(): any[] {
    if (this.texto === '' && this.prio === 'ALL') {
      return this.todolist;
    }

    if (this.prio !== 'ALL') {
      let novo = this.todolist.filter((item) => item.priority === this.prio);
      if (this.texto) {
        return novo.filter((item) =>
          String(item).toLowerCase().includes(this.texto.toLowerCase())
        );
      }
    }

    return this.todolist.filter((item) => {
      if (item.substr()) {
      }
    });
  }

  ngOnInit(): void {
    console.info('[INFO] Gettin all toodlists..');
    this.getAll();
  }

  getAll() {
    this.http.get<any[]>(`${this.baseUrl}/all`).subscribe((data) => {
      console.info(' [INFO] data before : ', data);
      data = data.map((item) => {
        return {
          ...item,
          dueDate: this.convert(item.dueDate),
        };
      });
      console.info('[info] dataaaa : ', data);
      this.todolist = data;
    });
  }

  update(newItem: any) {
    newItem = {
      ...newItem,
      dueDate: new Date(newItem.dueDate).getTime(),
    };

    this.http.post(`${this.baseUrl}/update`, newItem).subscribe((data) => {
      console.info('[INFO] Updated : ', data);
    });
  }

  updateValue(event: any, id: any, campo: any) {
    let value = event.target.value;
    console.info('vvalue:::: ', value);

    const index = this.todolist.findIndex((item) => item.id === id);
    if (campo === 'done') {
      value = event.target.checked;
    }

    this.todolist[index] = {
      ...this.todolist[index],
      [campo]: value,
    };

    this.update(this.todolist[index]);
  }

  addTask() {
    this.http.post(`${this.baseUrl}/add`, null).subscribe((data) => {
      console.info('[INFO] adding new item..');
      this.getAll();
    });
  }
}
