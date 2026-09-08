# Backend FastAPI
# uvicorn main:app --reload

import uvicorn
from fastapi import FastAPI, HTTPException
from pydantic import BaseModel

app = FastAPI(title="Programmatic uvicorn app")

class HelloRequest(BaseModel):
    name: str = "world"

@app.post("/hello")
def say_hello(request: HelloRequest):
    return {"message": f"Hello {request.name}"}
# @app.get("/college")
# def my_endpoint():
#     return {
#         "college":"KMIT"
#     }

# @app.get("/subjects")
# def my_endpoint(name: str):
#     return {
#         "message":f"Hello {name}"
#     }

# app.get("/hello/{name}")
# def say_hello(name: str):
#     return {"message": f"Hello {name}"}

# # in-memory "database"
# items: dict[int, dict] = {1: {"name": "pen", "price": 10.0}}


# class Item(BaseModel):
#     name: str
#     price: float


# class ItemPatch(BaseModel):
#     name: str | None = None
#     price: float | None = None


# @app.get("/")
# def root():
#     return {"message": "Hello FastAPI"}


# @app.get("/health")
# def read_health():
#     return {"status": "healthy"}


# @app.get("/items")
# def list_items():
#     return items


# @app.get("/items/{item_id}")
# def get_item(item_id: int):
#     if item_id not in items:
#         raise HTTPException(status_code=404, detail="Item not found")
#     return items[item_id]


# @app.post("/items", status_code=201)
# def create_item(item: Item):
#     new_id = max(items) + 1 if items else 1
#     items[new_id] = item.model_dump()
#     return {"id": new_id, **items[new_id]}


# @app.put("/items/{item_id}")
# def replace_item(item_id: int, item: Item):
#     if item_id not in items:
#         raise HTTPException(status_code=404, detail="Item not found")
#     items[item_id] = item.model_dump()
#     return {"id": item_id, **items[item_id]}


# @app.patch("/items/{item_id}")
# def update_item(item_id: int, patch: ItemPatch):
#     if item_id not in items:
#         raise HTTPException(status_code=404, detail="Item not found")
#     items[item_id].update(patch.model_dump(exclude_none=True))
#     return {"id": item_id, **items[item_id]}


# @app.delete("/items/{item_id}")
# def delete_item(item_id: int):
#     if item_id not in items:
#         raise HTTPException(status_code=404, detail="Item not found")
#     return {"deleted": items.pop(item_id)}


# @app.head("/ping")
# def ping():
#     return {"pong": True}


# if __name__ == "__main__":
#     uvicorn.run("main:app", host="127.0.0.1", port=8000, reload=True)
