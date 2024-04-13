from fastapi import FastAPI
import uvicorn
from pydantic import BaseModel
from starlette.responses import JSONResponse

import pickle
import numpy as np
import pandas as pd

class Item(BaseModel):
    PORT_CD: str
    inputDate: str

# app 개발
app = FastAPI()

