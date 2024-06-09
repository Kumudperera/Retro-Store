"use client";
import {
  Pagination,
  PaginationContent,
  PaginationLink,
  PaginationNext,
  PaginationPrevious,
} from "@/components/ui/pagination";
import { PageInfo, AppPagination } from "@/common/@types";
import { useEffect, useState } from "react";

interface Pagination extends AppPagination {
  onPaginationChange: (data: PageInfo) => void;
}

function RetroStorePagination(props: Pagination) {
  const [pageLength, setPageLength] = useState(10);
  const [paginationArray, setPaginationArray] = useState<number[] | never[]>(
    [],
  );

  const generatePaginationArray = () => {
    let pagination = [];
    const currentPageNo = props.current_page ? props.current_page : 1;
    const totalNoOfPages = props.total_pages ? props.total_pages : 1;

    if (currentPageNo - 2 >= 1) {
      pagination.push(currentPageNo - 2);
    }

    if (currentPageNo - 1 >= 1) {
      pagination.push(currentPageNo - 1);
    }

    pagination.push(currentPageNo);

    if (currentPageNo + 1 <= totalNoOfPages) {
      pagination.push(currentPageNo + 1);
    }

    if (currentPageNo + 2 <= totalNoOfPages) {
      pagination.push(currentPageNo + 2);
    }
    setPaginationArray(pagination);
  };

  useEffect(() => {
    if (props.page_length) {
      setPageLength(props.page_length);
    } else {
      generatePaginationArray();
    }
  }, [props.page_length]);

  useEffect(() => {
    generatePaginationArray();
  }, [pageLength, props.current_page, props.total_pages]);

  const currentPageNo = props.current_page ? props.current_page : 1;
  const totalNoOfPages = props.total_pages ? props.total_pages : 1;

  const onPagination = (e: number) => {
    props.onPaginationChange({
      rows: pageLength,
      page: e,
    });
  };

  return (
    <div className="mb-5 mr-5">
      <Pagination>
        <PaginationContent>
          {currentPageNo !== 1 && (
            <PaginationPrevious
              onClick={() => {
                onPagination(currentPageNo - 1);
              }}
              className="cursor-pointer"
            />
          )}

          {paginationArray.map((page) => {
            return (
              <PaginationLink
                key={page}
                isActive={page == currentPageNo}
                onClick={() => {
                  onPagination(page);
                }}
                className="cursor-pointer"
              >
                {page}
              </PaginationLink>
            );
          })}

          {currentPageNo !== totalNoOfPages && (
            <PaginationNext
              onClick={() => {
                onPagination(currentPageNo + 1);
              }}
              className="cursor-pointer"
            />
          )}
        </PaginationContent>
      </Pagination>
    </div>
  );
}

export default RetroStorePagination;
