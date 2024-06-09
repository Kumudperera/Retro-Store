import { Button } from "@/components/ui/button";
import Constants from "@/utils/constants";
import Link from "next/link";
export default function Home() {
  return (
    <div className="grid min-h-screen w-full md:grid-cols-[220px_1fr] lg:grid-cols-[280px_1fr]">
      <div className="hidden border-r bg-muted/40 md:block"></div>
      <div className="flex flex-col">
        <header className="flex h-14 items-center gap-4 border-b bg-muted/40 px-4 lg:h-[60px] lg:px-6"></header>

        <main className="flex flex-1 flex-col gap-4 p-4 lg:gap-6 lg:p-6">
          <div
            className="flex flex-1 items-center justify-center rounded-lg border border-dashed shadow-sm"
            x-chunk="dashboard-02-chunk-1"
          >
            <div className="flex flex-col items-center gap-1 text-center">
              <h3 className="text-2xl font-bold tracking-tight">
                You have no products
              </h3>
              <p className="text-sm text-muted-foreground">
                You can start selling as soon as you add a product.
              </p>
              <Button className="mt-4">
                <Link
                  href={Constants.PAGES.DASHBOARD}
                  className="mx-[-0.65rem] md:mx-0 flex items-center gap-4 md:gap-3 rounded-lg px-3 py-2 text-muted-foreground hover:text-primary"
                >
                  Login
                </Link>
              </Button>
            </div>
          </div>
        </main>
      </div>
    </div>
  );
}
